//
//  HomeVM.swift
//  BaseiOSUIKit
//
//  Created by Ali Taha on 5.12.2023.
//

import Foundation
import Combine

class HomeVM {
    private var cancelables: Set<AnyCancellable> = []
    private var getAllCoinsUseCase: GetAllCoinsUseCase
    
    private(set) var state: CurrentValueSubject<HomeState, Never> = CurrentValueSubject(HomeState())
    
    init(getAllCoinsUseCase: GetAllCoinsUseCase) {
        self.getAllCoinsUseCase = getAllCoinsUseCase
        self.getAllCoinsUseCase.resource
            .receive(on: DispatchQueue.main)
            .sink(
                receiveCompletion: {
                    print($0)
                },
                receiveValue: { resource in
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
                                error: "", coins: data
                            )
                    }
                }
            ).store(in: &cancelables)
    }
    
    func getCoins() async {
        if (self.state.value.coins.isEmpty) {
            await self.getAllCoinsUseCase.fetchData()
        }
    }
}
