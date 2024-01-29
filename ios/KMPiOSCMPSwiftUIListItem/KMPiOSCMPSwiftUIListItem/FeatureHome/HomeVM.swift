//
//  HomeVM.swift
//  KMPiOSCMPSwiftUIListItem
//
//  Created by Ali Taha on 14.12.2023.
//

import Foundation
import composeBase

class HomeVM: ObservableObject {
    private let stateMachine: HomeStateMachine
    private var handle: DisposableHandle?
    
    @Published
    var currentState: HomeState = HomeState(
        coins: [],
        isLoading: false,
        error: ""
    )
    
    init() {
        self.stateMachine = HomeStateMachine()
    }
    
    func startObserving() {
        self.handle = self.stateMachine.state.subscribe(onCollect: { state in
            if let collectedState = state {
                self.currentState = collectedState
            }
        })
    }
    
    func dispose() {
        self.stateMachine.reset()
        self.handle?.dispose()
    }
}
