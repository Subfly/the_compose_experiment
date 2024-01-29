//
//  DetailState.swift
//  BaseiOSUIKit
//
//  Created by Ali Taha on 5.12.2023.
//

import Foundation

struct DetailState {
    var isLoading: Bool = true
    var error: String = ""
    var coin: CoinDetailModel? = nil
    
    func copy(
        isLoading: Bool? = nil,
        error: String? = nil,
        coin: CoinDetailModel? = nil
    ) -> DetailState {
        return DetailState(
            isLoading: isLoading ?? self.isLoading,
            error: error ?? self.error,
            coin: coin ?? self.coin
        )
    }
}
