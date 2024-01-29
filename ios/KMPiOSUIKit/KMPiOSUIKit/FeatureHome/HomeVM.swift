//
//  HomeVM.swift
//  KMPiOSUIKit
//
//  Created by Ali Taha on 14.12.2023.
//

import Foundation
import Combine
import kmpktorcoinbase

class HomeVM {
    private let stateMachine: HomeStateMachine
    private var handle: DisposableHandle?
    
    private(set) var state: CurrentValueSubject<HomeState, Never> = CurrentValueSubject(
        HomeState(
            coins: [],
            isLoading: true,
            error: ""
        )
    )
    
    init() {
        self.stateMachine = HomeStateMachine()
    }
    
    func startObserving() {
        self.handle = self.stateMachine.state.subscribe(onCollect: { state in
            if let collectedState = state {
                self.state.send(collectedState)
            }
        })
    }
    
    func dispose() {
        self.stateMachine.reset()
        self.handle?.dispose()
    }
}
