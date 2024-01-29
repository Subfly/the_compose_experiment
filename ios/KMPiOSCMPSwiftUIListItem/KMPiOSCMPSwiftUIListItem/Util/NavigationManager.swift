//
//  NavigationManager.swift
//  KMPiOSCMPSwiftUIListItem
//
//  Created by Ali Taha on 14.12.2023.
//

import Foundation
import SwiftUI

class NavigationManager: ObservableObject {
    @Published
    var navigationPath = NavigationPath()
    
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
}
