//
//  CoinMapper.swift
//  BaseiOSUIKit
//
//  Created by Ali Taha on 5.12.2023.
//

import Foundation

extension CoinDto {
    func toCoinDetailModel() throws -> CoinDetailModel {
        guard let id = self.id else {
            throw InvalidIdException()
        }
        
        var coinType: CoinType = .invalid
        
        switch self.type {
        case CoinType.coin.rawValue:
            coinType = .coin
        case CoinType.token.rawValue:
            coinType = .token
        default:
            coinType = .invalid
        }
        
        return CoinDetailModel(
            id: id,
            name: self.name.orEmpty(),
            symbol: self.symbol.orEmpty(),
            message: self.message.orEmpty(),
            description: self.description.orEmpty(),
            isActive: self.is_active.orFalse(),
            type: coinType
        )
    }
    
    func toCoinHomeModel() throws -> CoinHomeModel {
        guard let id = self.id else {
            throw InvalidIdException()
        }
        
        var coinType: CoinType = .invalid
        
        switch self.type {
        case CoinType.coin.rawValue:
            coinType = .coin
        case CoinType.token.rawValue:
            coinType = .token
        default:
            coinType = .invalid
        }
        
        return CoinHomeModel(
            id: id,
            name: self.name.orEmpty(),
            symbol: self.symbol.orEmpty(),
            isActive: self.is_active.orFalse(),
            isNew: self.is_new.orFalse(),
            type: coinType
        )
    }
}
