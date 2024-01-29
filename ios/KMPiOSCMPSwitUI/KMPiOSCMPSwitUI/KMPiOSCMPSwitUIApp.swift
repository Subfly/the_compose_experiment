//
//  KMPiOSCMPSwitUIApp.swift
//  KMPiOSCMPSwitUI
//
//  Created by Ali Taha on 10.12.2023.
//

import SwiftUI

@main
struct KMPiOSCMPSwitUIApp: App {
    @StateObject
    var navigationManager = NavigationManager()
    
    var body: some Scene {
        WindowGroup {
            NavigationStack(path: $navigationManager.navigationPath) {
                HomeView()
                    .environmentObject(navigationManager)
                    .navigationDestination(
                        for: Screens.self
                    ) { screen in
                        switch (screen) {
                        case .detail(let id, let symbol, let activity):
                            DetailView(
                                coinId: id,
                                coinSymbol: symbol,
                                coinActivity: activity
                            )
                        }
                    }
            }
        }
    }
}
