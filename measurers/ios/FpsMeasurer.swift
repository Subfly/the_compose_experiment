//
//  FpsMeasurer.swift
//  BaseiOSUIKit
//
//  Created by Ali Taha on 19.12.2023.
//

import Foundation
import QuartzCore

class FpsMeasurer {
    private var step = 0
    private var frameCount = 0
    private var accumulatedFPS = 0.0
    private var startTime: CFTimeInterval = 0
    private var fpsValues: [Int:Double]  = [:]
    private lazy var displayLink: CADisplayLink = {
        return CADisplayLink(target: self, selector: #selector(handleFrame(_:)))
    }()

    func startCapturingFrames(duration: TimeInterval) {
        self.startTime = CACurrentMediaTime()

        // Use [weak self] to avoid a strong reference cycle
        Timer.scheduledTimer(withTimeInterval: duration, repeats: false) { [weak self] _ in
            self?.stopCapturing()
        }

        displayLink.add(to: RunLoop.current, forMode: RunLoop.Mode.common)
    }
    
    @objc private func handleFrame(_ displayLink: CADisplayLink) {
        frameCount += 1
                
        // Calculate FPS
        let currentTime = CACurrentMediaTime()
        let elapsedTime = currentTime - startTime
        let fps = Double(frameCount) / elapsedTime
        
        // Accumulate FPS for each second
        accumulatedFPS += fps
        
        // If one second has passed, store the average FPS
        if elapsedTime >= 1.0 {
            step += 1
            let averageFPS = accumulatedFPS / Double(frameCount)
            fpsValues[step] = averageFPS
            
            // Reset counters for the next second
            frameCount = 0
            accumulatedFPS = 0.0
            startTime = currentTime
        }
    }
    
    private func stopCapturing() {
        self.displayLink.invalidate()
        self.writeToFileAndUpload()
    }
    
    private func writeToFileAndUpload() {
        let fpsStrings = fpsValues.map { (key: Int, value: Double) in
            "\(key), \(value)"
        }
        let fps = fpsStrings.joined(separator: "\n")
        //print("Fps Values: \(fps)")
        let fileManager = FileManager.default
        do {
            let path = try fileManager.url(
                for: .documentDirectory,
                in: .allDomainsMask,
                appropriateFor: nil,
                create: true
            )
            let fileURL = path.appendingPathComponent("BaseIOSUIKit_fps_measures.csv")
            try fps.write(to: fileURL, atomically: true, encoding: .utf8)
            print(fileURL)
        } catch {
            print("error creating file")
        }
    }
}
