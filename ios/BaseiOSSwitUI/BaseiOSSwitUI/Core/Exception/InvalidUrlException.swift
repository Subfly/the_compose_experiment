//
//  InvalidUrlException.swift
//  BaseiOSSwitUI
//
//  Created by Ali Taha on 4.12.2023.
//

import Foundation

struct InvalidUrlException: Error {
    let message: String = "Cannot be able to build URL for this task"
}
