//
//  HomeView.swift
//  BaseiOSSwitUI
//
//  Created by Ali Taha on 4.12.2023.
//

import SwiftUI

struct HomeView: View {
    @StateObject
    private var vm = HomeVM(
        getAllCoinsUseCase: GetAllCoinsUseCase(
            repository: CoinPaprikaHomeRepository()
        )
    )
    
    var body: some View {
        ZStack {
            if vm.state.isLoading {
                ProgressView()
            }
            
            if vm.state.error.isEmpty.not() {
                Text(vm.state.error)
            }
            
            if vm.state.coins.isEmpty.not() {
                ScrollView() {
                    LazyVStack {
                        ForEach(vm.state.coins) { coin in
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
            
            if vm.state.isLoading.not()
                && vm.state.error.isEmpty
                && vm.state.coins.isEmpty {
                Text("Nothing to show...")
            }
        }
        .navigationTitle("Home")
        .toolbarColorScheme(.dark, for: .navigationBar)
        .toolbarBackground(.black, for: .navigationBar)
        .toolbarBackground(.visible, for: .navigationBar)
        .task {
            await vm.getCoins()
        }
    }
}

#Preview {
    HomeView()
}
