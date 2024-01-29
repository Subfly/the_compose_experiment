//
//  CMPSwiftUIApp.swift
//  CMPSwiftUI
//
//  Created by Ali Taha on 15.12.2023.
//

import SwiftUI

@main
struct CMPSwiftUIApp: App {
    @StateObject
    var navigationManager = NavigationManager.shared
    
    var body: some Scene {
        WindowGroup {
            NavigationStack(path: $navigationManager.navigationPath) {
                HomeView()
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
