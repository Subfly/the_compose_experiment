//
//  Resource.swift
//  BaseiOSUIKit
//
//  Created by Ali Taha on 5.12.2023.
//

import Foundation

enum Resource<T> {
    case error(message: String)
    case loading
    case success(data: T)
}
