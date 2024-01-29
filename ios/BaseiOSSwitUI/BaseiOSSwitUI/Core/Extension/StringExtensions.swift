//
//  StringExtension.swift
//  BaseiOSSwitUI
//
//  Created by Ali Taha on 4.12.2023.
//

import Foundation

extension String? {
    func orEmpty() -> String {
        return self ?? Constants.EMPTY_STRING
    }
}
