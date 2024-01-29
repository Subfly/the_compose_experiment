//
//  HomeCoinCellControllerRepository.swift
//  KMPiOSCMPUIKitListItem
//
//  Created by Ali Taha on 14.12.2023.
//

import Foundation
import UIKit

class HomeCoinCellControllerRepository {
    static let shared = HomeCoinCellControllerRepository()
    
    private var controllers: [String:UIViewController] = [:]
    
    private init() {}
    
    func getController(for _coinId: String) -> UIViewController? {
        return self.controllers[_coinId]
    }
    
    func addController(for _coinId: String, controller: UIViewController) {
        if (self.getController(for: _coinId) == nil) {
            self.controllers[_coinId] = controller
        }
    }
    
    func clear() {
        controllers.removeAll()
    }
}
