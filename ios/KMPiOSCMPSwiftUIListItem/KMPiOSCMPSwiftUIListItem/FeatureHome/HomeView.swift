//
//  HomeView.swift
//  KMPiOSCMPSwiftUIListItem
//
//  Created by Ali Taha on 14.12.2023.
//

import Foundation
import SwiftUI

struct HomeView: View {
    @EnvironmentObject
    var navigationManager: NavigationManager
    
    @StateObject
    private var vm = HomeVM()
    
    var body: some View {
        ZStack {
            if self.vm.currentState.isLoading {
                ProgressView()
            }
            
            if self.vm.currentState.error.isEmpty.not() {
                Text(vm.currentState.error)
            }
            
            if self.vm.currentState.coins.isEmpty.not() {
                ScrollView {
                    LazyVStack {
                        ForEach(self.vm.currentState.coins, id: \.id) { coin in
                            HomeItemView(coin: coin) { coinModel in
                                self.navigationManager.goToDetail(
                                    of: coinModel.id,
                                    coinSymbol: coinModel.symbol,
                                    coinActivity: coinModel.isActive
                                )
                            }
                            .frame(
                                height: 60
                            )
                        }
                    }
                }
            }
            
            if self.vm.currentState.isLoading.not()
                && self.vm.currentState.error.isEmpty
                && self.vm.currentState.coins.isEmpty {
                Text("Nothing to show...")
            }
        }
        .navigationTitle("Home")
        .toolbarColorScheme(.dark, for: .navigationBar)
        .toolbarBackground(.black, for: .navigationBar)
        .toolbarBackground(.visible, for: .navigationBar)
        .task {
            self.vm.startObserving()
        }
        .onDisappear {
            self.vm.dispose()
        }
    }
}

#Preview {
    HomeView()
}
