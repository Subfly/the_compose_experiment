//
//  InvalidResponseException.swift
//  BaseiOSUIKit
//
//  Created by Ali Taha on 5.12.2023.
//

import Foundation

struct InvalidResponseException: Error {
    let message: String = "Response returned with a response code other than 200"
}
