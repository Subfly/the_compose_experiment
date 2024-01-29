//
//  CoinPaprikaHomeDao.swift
//  BaseiOSSwitUI
//
//  Created by Ali Taha on 4.12.2023.
//

import Foundation

protocol CoinPaprikaHomeDao {
    func getAllCoins() async throws -> [CoinDto]
}
