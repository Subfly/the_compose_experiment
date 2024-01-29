//
//  InvalidUrlException.swift
//  BaseiOSUIKit
//
//  Created by Ali Taha on 5.12.2023.
//

import Foundation

struct InvalidUrlException: Error {
    let message: String = "Cannot be able to build URL for this task"
}
