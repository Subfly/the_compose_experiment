//
//  DetailVM.swift
//  BaseiOSSwitUI
//
//  Created by Ali Taha on 5.12.2023.
//

import Foundation
import Combine

class DetailVM: ObservableObject {
    private var cancelables: Set<AnyCancellable> = []
    private var getCoinDetailUseCase: GetCoinByIdUseCase
    
    @Published
    private(set) var state: DetailState = DetailState()
    
    init(getCoinDetailByIdUseCase: GetCoinByIdUseCase) {
        self.getCoinDetailUseCase = getCoinDetailByIdUseCase
        self.getCoinDetailUseCase.resource
            .receive(on: DispatchQueue.main)
            .sink { _ in
                
            } receiveValue: { resource in
                switch resource {
                    case .loading:
                        self.state = self.state.copy(
                            isLoading: true,
                            error: ""
                        )
                    case .error(let message):
                        self.state = self.state.copy(
                            isLoading: false,
                            error: message
                        )
                    case .success(let data):
                        self.state = self.state.copy(
                            isLoading: false,
                            error: "",
                            coin: data
                        )
                }
            }.store(in: &cancelables)
    }
    
    func getCoinDetail(coinId: String) async {
        await self.getCoinDetailUseCase.fetchData(coinId: coinId)
    }
}
