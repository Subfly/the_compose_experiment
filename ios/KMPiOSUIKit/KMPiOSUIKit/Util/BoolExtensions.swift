//
//  BoolExtensions.swift
//  KMPiOSUIKit
//
//  Created by Ali Taha on 14.12.2023.
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
