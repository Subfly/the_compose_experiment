//
//  GetAllCoinsUseCase.swift
//  BaseiOSUIKit
//
//  Created by Ali Taha on 5.12.2023.
//

import Foundation
import Combine

class GetAllCoinsUseCase {
    private let repository: CoinPaprikaHomeDao
    
    var resource: CurrentValueSubject<Resource<[CoinHomeModel]>, Never>

    init(repository: CoinPaprikaHomeDao) {
        self.repository = repository
        self.resource = CurrentValueSubject(.loading)
    }

    func fetchData() async {
        do {
            resource.send(.loading)
            let coinsFetched = try await repository.getAllCoins().map { try $0.toCoinHomeModel() }
            resource.send(.success(data: coinsFetched))
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
