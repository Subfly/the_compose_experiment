//
//  CoinType.swift
//  BaseiOSUIKit
//
//  Created by Ali Taha on 5.12.2023.
//

import Foundation
import SwiftUI

enum CoinType: String {
    case coin = "coin", token = "token", invalid = "-"
    
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
