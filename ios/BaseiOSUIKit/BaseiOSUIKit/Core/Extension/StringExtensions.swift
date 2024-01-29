//
//  StringExtensions.swift
//  BaseiOSUIKit
//
//  Created by Ali Taha on 5.12.2023.
//

import Foundation

extension String? {
    func orEmpty() -> String {
        return self ?? Constants.EMPTY_STRING
    }
}
