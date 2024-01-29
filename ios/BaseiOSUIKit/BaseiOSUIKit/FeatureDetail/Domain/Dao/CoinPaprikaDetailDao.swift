//
//  CoinPaprikaDetailDao.swift
//  BaseiOSUIKit
//
//  Created by Ali Taha on 5.12.2023.
//

import Foundation

protocol CoinPaprikaDetailDao {
    func getCoinDetail(for id: String) async throws -> CoinDto
}
