//
//  MemoryMeasurer.swift
//  BaseiOSUIKit
//
//  Created by Ali Taha on 19.12.2023.
//

import Foundation

class MemoryMeasurer {
    private var step = 0
    private var timer: Timer?
    private var memoryUsageValues: [Int:Double] = [:]

    func startMeasuringMemoryUsage(duration: TimeInterval) {
        // Set up a timer to measure memory usage at regular intervals
        timer = Timer.scheduledTimer(withTimeInterval: 1.0, repeats: true) { _ in
            self.measureMemoryUsage()
        }
        RunLoop.main.add(timer!, forMode: RunLoop.Mode.common)

        // Set up a timer to stop measuring after the specified duration
        Timer.scheduledTimer(withTimeInterval: duration, repeats: false) { _ in
            self.stopMeasuring()
        }
    }
    
    private func memoryFootprint() -> Float? {
        let TASK_VM_INFO_COUNT = mach_msg_type_number_t(MemoryLayout<task_vm_info_data_t>.size / MemoryLayout<integer_t>.size)
        let TASK_VM_INFO_REV1_COUNT = mach_msg_type_number_t(MemoryLayout.offset(of: \task_vm_info_data_t.min_address)! / MemoryLayout<integer_t>.size)
        var info = task_vm_info_data_t()
        var count = TASK_VM_INFO_COUNT
        let kr = withUnsafeMutablePointer(to: &info) { infoPtr in
            infoPtr.withMemoryRebound(to: integer_t.self, capacity: Int(count)) { intPtr in
                task_info(mach_task_self_, task_flavor_t(TASK_VM_INFO), intPtr, &count)
            }
        }
        guard
            kr == KERN_SUCCESS,
            count >= TASK_VM_INFO_REV1_COUNT
            else { return nil }
        
        let usedBytes = Float(info.phys_footprint)
        return usedBytes
    }
        
    private func measureMemoryUsage() {
        let usedBytes: UInt64? = UInt64(self.memoryFootprint() ?? 0)
        let usedMB = Double(usedBytes ?? 0) / 1024 / 1024
        step += 1
        self.memoryUsageValues[self.step] = usedMB
     }

    private func stopMeasuring() {
        self.timer?.invalidate()
        printMemoryUsageValues()
    }

    private func printMemoryUsageValues() {
        let memoryStrings = memoryUsageValues.map { (key: Int, value: Double) in
            "\(key), \(value)"
        }
        let memory = memoryStrings.joined(separator: "\n")
        //print("Fps Values: \(fps)")
        let fileManager = FileManager.default
        do {
            let path = try fileManager.url(
                for: .documentDirectory,
                in: .allDomainsMask,
                appropriateFor: nil,
                create: true
            )
            let fileURL = path.appendingPathComponent("BaseIOSUIKit_memory_measures.csv")
            try memory.write(to: fileURL, atomically: true, encoding: .utf8)
            print(fileURL)
        } catch {
            print("error creating file")
        }
    }
}
