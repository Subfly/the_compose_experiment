//
//  DetailVM.swift
//  BaseiOSUIKit
//
//  Created by Ali Taha on 5.12.2023.
//

import Foundation
import Combine

class DetailVM {
    private var cancelables: Set<AnyCancellable> = []
    private var getCoinDetailUseCase: GetCoinByIdUseCase
    
    private(set) var state: CurrentValueSubject<DetailState, Never> = CurrentValueSubject(DetailState())
    
    init(getCoinDetailByIdUseCase: GetCoinByIdUseCase) {
        self.getCoinDetailUseCase = getCoinDetailByIdUseCase
        self.getCoinDetailUseCase.resource
            .receive(on: DispatchQueue.main)
            .sink { _ in
                
            } receiveValue: { resource in
                let currentState = self.state.value
                switch resource {
                    case .loading:
                    self.state.value = currentState.copy(
                            isLoading: true,
                            error: ""
                        )
                    case .error(let message):
                    self.state.value = currentState.copy(
                            isLoading: false,
                            error: message
                        )
                    case .success(let data):
                    self.state.value = currentState.copy(
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
