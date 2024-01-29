//
//  BoolExtensions.swift
//  BaseiOSSwitUI
//
//  Created by Ali Taha on 4.12.2023.
//

import Foundation

extension Bool? {
    func orFalse() -> Bool {
        return self ?? false
    }
}

extension Bool {
    func not() -> Bool {
        return !self
    }
}
