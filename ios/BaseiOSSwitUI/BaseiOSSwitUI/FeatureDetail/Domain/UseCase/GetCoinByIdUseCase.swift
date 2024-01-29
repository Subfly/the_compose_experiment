//
//  GetCoinByIdUseCase.swift
//  BaseiOSSwitUI
//
//  Created by Ali Taha on 5.12.2023.
//

import Foundation
import Combine

class GetCoinByIdUseCase {
    private let repository: CoinPaprikaDetailDao
    
    var resource: CurrentValueSubject<Resource<CoinDetailModel>, Never>

    init(repository: CoinPaprikaDetailDao) {
        self.repository = repository
        self.resource = CurrentValueSubject(.loading)
    }

    func fetchData(coinId: String) async {
        do {
            resource.send(.loading)
            let coinFetched = try await repository.getCoinDetail(for: coinId).toCoinDetailModel()
            resource.send(.success(data: coinFetched))
        } catch let error as InvalidUrlException {
            resource.send(.error(message: error.message))
        } catch let error as InvalidIdException {
            resource.send(.error(message: error.message))
        } catch let error as InvalidResponseException {
            resource.send(.error(message: error.message))
        } catch {
            resource.send(.error(message: error.localizedDescription))
        }
    }
}
