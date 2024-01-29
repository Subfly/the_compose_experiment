//
//  HomeView.swift
//  KMPiOSSwitUI
//
//  Created by Ali Taha on 10.12.2023.
//

import SwiftUI
import kmpktorcoinbase

struct HomeView: View {
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
                ScrollView() {
                    LazyVStack {
                        ForEach(
                            self.vm.currentState.coins,
                            id: \.id
                        ) { coin in
                            NavigationLink {
                                DetailView(
                                    coinId: coin.id,
                                    coinSymbol: coin.symbol,
                                    coinActivity: coin.isActive
                                )
                            } label: {
                                HomeViewItem(model: coin)
                            }
                            .buttonStyle(.plain)

                        }
                    }
                }.scrollIndicators(.never)
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
