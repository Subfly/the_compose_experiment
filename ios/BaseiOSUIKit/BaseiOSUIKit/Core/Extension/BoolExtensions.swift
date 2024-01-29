//
//  BoolExtensions.swift
//  BaseiOSUIKit
//
//  Created by Ali Taha on 5.12.2023.
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
