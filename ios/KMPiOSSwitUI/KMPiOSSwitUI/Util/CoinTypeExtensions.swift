//
//  CoinTypeExtensions.swift
//  KMPiOSSwitUI
//
//  Created by Ali Taha on 10.12.2023.
//

import Foundation
import SwiftUI
import kmpktorcoinbase

extension CoinType {
    func getIcon() -> ImageResource {
        switch self {
        case .coin:
            return .coinType
        case .token:
            return .tokenType
        default:
            return .invalidType
        }
    }
}
