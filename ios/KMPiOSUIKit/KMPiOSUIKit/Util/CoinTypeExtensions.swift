//
//  CoinTypeExtensions.swift
//  KMPiOSUIKit
//
//  Created by Ali Taha on 14.12.2023.
//

import Foundation
import UIKit
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
