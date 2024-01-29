//
//  BoolExtensions.swift
//  KMPiOSSwitUI
//
//  Created by Ali Taha on 10.12.2023.
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
