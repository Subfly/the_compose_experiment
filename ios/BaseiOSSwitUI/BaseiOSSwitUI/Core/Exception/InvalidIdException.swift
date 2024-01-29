//
//  InvalidIdException.swift
//  BaseiOSSwitUI
//
//  Created by Ali Taha on 4.12.2023.
//

import Foundation

struct InvalidIdException: Error {
    let message: String = "Id of Coin should not be null"
}
