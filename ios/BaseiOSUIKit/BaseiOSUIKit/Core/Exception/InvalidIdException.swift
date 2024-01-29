//
//  InvalidIdException.swift
//  BaseiOSUIKit
//
//  Created by Ali Taha on 5.12.2023.
//

import Foundation

struct InvalidIdException: Error {
    let message: String = "Id of Coin should not be null"
}

