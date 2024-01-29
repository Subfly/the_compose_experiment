//
//  CoinHomeModel.swift
//  BaseiOSUIKit
//
//  Created by Ali Taha on 5.12.2023.
//

import Foundation

struct CoinHomeModel: Identifiable {
    let id: String
    var name: String = Constants.EMPTY_STRING
    var symbol: String = Constants.EMPTY_STRING
    var isActive: Bool = false
    var isNew: Bool = false
    var type: CoinType = .invalid
}
