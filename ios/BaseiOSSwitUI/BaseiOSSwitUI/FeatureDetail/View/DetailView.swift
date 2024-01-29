//
//  DetailView.swift
//  BaseiOSSwitUI
//
//  Created by Ali Taha on 5.12.2023.
//

import SwiftUI

struct DetailView: View {
    let coinId: String
    let coinSymbol: String
    let coinActivity: Bool
    
    @StateObject
    private var vm = DetailVM(
        getCoinDetailByIdUseCase: GetCoinByIdUseCase(
            repository: CoinPaprikaDetailRepository()
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
            
            if let coin = vm.state.coin {
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
                        Text(coin.description)
                            .font(.body)
                            .padding()
                        
                    }
                }.scrollIndicators(.never)
            }
            
            if vm.state.isLoading.not()
                && vm.state.error.isEmpty
                && vm.state.coin == nil {
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
            await vm.getCoinDetail(coinId: coinId)
        }
    }
}

#Preview {
    DetailView(
        coinId: "-1",
        coinSymbol: "BTC",
        coinActivity: true
    )
}
