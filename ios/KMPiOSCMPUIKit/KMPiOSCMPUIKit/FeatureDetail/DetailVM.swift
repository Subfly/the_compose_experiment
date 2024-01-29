//
//  DetailVM.swift
//  KMPiOSCMPUIKit
//
//  Created by Ali Taha on 14.12.2023.
//

import Foundation
import Combine
import composeBase

class DetailVM {
    private let stateMachine: CoinDetailStateMachine
    private var handle: DisposableHandle?
    
    private(set) var state: CurrentValueSubject<CoinDetailState, Never> = CurrentValueSubject(
        CoinDetailState(
            coin: nil,
            isLoading: true,
            error: ""
        )
    )
    
    init() {
        self.stateMachine = CoinDetailStateMachine()
    }
    
    func startObserving() {
        self.handle = self.stateMachine.state.subscribe(onCollect: { state in
            if let collectedState = state {
                self.state.send(collectedState)
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
