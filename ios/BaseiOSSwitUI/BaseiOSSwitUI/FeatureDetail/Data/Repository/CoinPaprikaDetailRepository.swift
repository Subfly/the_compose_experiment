//
//  CoinPaprikaDetailRepository.swift
//  BaseiOSSwitUI
//
//  Created by Ali Taha on 5.12.2023.
//

import Foundation

class CoinPaprikaDetailRepository: CoinPaprikaDetailDao {
    private let coinIdPath = "/coins/"
    
    func getCoinDetail(for id: String) async throws -> CoinDto {
        let baseUrl = Constants.COIN_PAPRIKA_BASE_URL
        guard let url = URL(string: baseUrl + coinIdPath + id) else {
            throw InvalidUrlException()
        }
        
        let (data, response) = try await URLSession.shared.data(from: url)
        guard let httpResponse = response as? HTTPURLResponse, httpResponse.statusCode == 200 else {
            throw InvalidResponseException()
        }
        
        let decoder = JSONDecoder()
        return try decoder.decode(CoinDto.self, from: data)
    }
}
