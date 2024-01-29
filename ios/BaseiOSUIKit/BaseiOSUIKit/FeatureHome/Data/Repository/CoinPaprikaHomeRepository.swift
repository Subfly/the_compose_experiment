//
//  CoinPaprikaHomeRepository.swift
//  BaseiOSUIKit
//
//  Created by Ali Taha on 5.12.2023.
//

import Foundation

class CoinPaprikaHomeRepository: CoinPaprikaHomeDao {
    private let allCoinsPath = "/coins"
    
    func getAllCoins() async throws -> [CoinDto] {
        let baseUrl = Constants.COIN_PAPRIKA_BASE_URL
        guard let url = URL(string: baseUrl + allCoinsPath) else {
            throw InvalidUrlException()
        }
        
        let (data, response) = try await URLSession.shared.data(from: url)
        guard let httpResponse = response as? HTTPURLResponse, httpResponse.statusCode == 200 else {
            throw InvalidIdException()
        }
        
        let decoder = JSONDecoder()
        return try decoder.decode([CoinDto].self, from: data)
     }
}
