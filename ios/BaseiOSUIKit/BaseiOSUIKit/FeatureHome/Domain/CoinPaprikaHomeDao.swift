//
//  CoinPaprikaHomeDao.swift
//  BaseiOSUIKit
//
//  Created by Ali Taha on 5.12.2023.
//

import Foundation

protocol CoinPaprikaHomeDao {
    func getAllCoins() async throws -> [CoinDto]
}
