//
//  HomeVM.swift
//  BaseiOSSwitUI
//
//  Created by Ali Taha on 4.12.2023.
//

import Foundation
import Combine

class HomeVM: ObservableObject {
    private var cancelables: Set<AnyCancellable> = []
    private var getAllCoinsUseCase: GetAllCoinsUseCase
    
    @Published
    private(set) var state: HomeState = HomeState()
    
    init(getAllCoinsUseCase: GetAllCoinsUseCase) {
        self.getAllCoinsUseCase = getAllCoinsUseCase
        self.getAllCoinsUseCase.resource
            .receive(on: DispatchQueue.main)
            .sink(
                receiveCompletion: {
                    print($0)
                },
                receiveValue: { resource in
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
                                error: "", coins: data
                            )
                    }
                }
            ).store(in: &cancelables)
    }
    
    func getCoins() async {
        if (self.state.coins.isEmpty) {
            await self.getAllCoinsUseCase.fetchData()
        }
    }
}
