//
//  HomeState.swift
//  BaseiOSUIKit
//
//  Created by Ali Taha on 5.12.2023.
//

import Foundation

struct HomeState {
    var isLoading: Bool = true
    var error: String = ""
    var coins: [CoinHomeModel] = []
    
    func copy(
        isLoading: Bool? = nil,
        error: String? = nil,
        coins: [CoinHomeModel]? = nil
    ) -> HomeState {
        return HomeState(
            isLoading: isLoading ?? self.isLoading,
            error: error ?? self.error,
            coins: coins ?? self.coins
        )
    }
}
