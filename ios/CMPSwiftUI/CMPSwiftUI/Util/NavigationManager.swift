//
//  NavigationManager.swift
//  CMPSwiftUI
//
//  Created by Ali Taha on 15.12.2023.
//

import Foundation
import SwiftUI

class NavigationManager: ObservableObject {
    @Published
    var navigationPath = NavigationPath()
    
    static let shared = NavigationManager()
    
    private init() {}
    
    func goToDetail(
        of coinId: String,
        coinSymbol: String,
        coinActivity: Bool
    ) {
        navigationPath.append(
            Screens.detail(
                id: coinId,
                symbol: coinSymbol,
                activity: coinActivity
            )
        )
    }
    
    func pop() {
        navigationPath.removeLast()
    }
}
