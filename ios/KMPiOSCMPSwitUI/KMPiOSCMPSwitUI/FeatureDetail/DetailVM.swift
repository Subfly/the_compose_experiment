//
//  DetailVM.swift
//  KMPiOSCMPSwitUI
//
//  Created by Ali Taha on 14.12.2023.
//

import Foundation
import composeBase

class DetailVM: ObservableObject {
    private let stateMachine: CoinDetailStateMachine
    private var handle: DisposableHandle?
    
    @Published
    var currentState: CoinDetailState = CoinDetailState(
        coin: nil,
        isLoading: true,
        error: ""
    )
    
    init() {
        self.stateMachine = CoinDetailStateMachine()
    }
    
    func startObserving() {
        self.handle = self.stateMachine.state.subscribe(onCollect: { state in
            if let collectedState = state {
                self.currentState = collectedState
            }
        })
    }
    
    func fetchCoin(with id: String) {
        self.stateMachine.getCoinById(id: id)
    }
    
    func dispose() {
        self.stateMachine.reset()
        self.handle?.dispose()
    }
}
