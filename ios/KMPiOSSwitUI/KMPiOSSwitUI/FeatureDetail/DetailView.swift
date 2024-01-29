//
//  DetailView.swift
//  KMPiOSSwitUI
//
//  Created by Ali Taha on 10.12.2023.
//

import SwiftUI

struct DetailView: View {
    let coinId: String
    let coinSymbol: String
    let coinActivity: Bool
    
    @StateObject
    private var vm = DetailVM()
    
    var body: some View {
        ZStack {
            if self.vm.currentState.isLoading {
                ProgressView()
            }
            
            if self.vm.currentState.error.isEmpty.not() {
                Text(vm.currentState.error)
            }
            
            if let coin = self.vm.currentState.coin {
                ScrollView() {
                    VStack {
                        Image(coin.type.getIcon())
                            .resizable()
                            .frame(width: 200, height: 200)
                            .scaledToFill()
                            .padding(.vertical)
                        Text(coin.name)
                            .font(.largeTitle)
                            .bold()
                        Text(coin.message)
                            .font(.body)
                            .padding(.bottom)
                        Text(coin.coinDescription)
                            .font(.body)
                            .padding()
                        
                    }
                }.scrollIndicators(.never)
            }
            
            if self.vm.currentState.isLoading.not()
                && self.vm.currentState.error.isEmpty
                && self.vm.currentState.coin == nil {
                Text("Nothing to show...")
            }
        }
        .toolbar {
            ToolbarItem(placement: .topBarTrailing) {
                Circle()
                    .frame(width: 12, height: 12)
                    .scaledToFill()
                    .foregroundStyle(coinActivity ? .green : .gray)
            }
        }
        .navigationTitle(coinSymbol)
        .toolbarColorScheme(.dark, for: .navigationBar)
        .toolbarBackground(.black, for: .navigationBar)
        .toolbarBackground(.visible, for: .navigationBar)
        .task {
            self.vm.startObserving()
            self.vm.fetchCoin(with: coinId)
        }
        .onDisappear(perform: {
            self.vm.dispose()
        })
    }
}

#Preview {
    DetailView(
        coinId: "-1",
        coinSymbol: "BTC",
        coinActivity: true
    )
}
