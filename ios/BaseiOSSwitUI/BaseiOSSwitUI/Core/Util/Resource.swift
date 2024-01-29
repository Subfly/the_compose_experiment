//
//  Resource.swift
//  BaseiOSSwitUI
//
//  Created by Ali Taha on 4.12.2023.
//

import Foundation

enum Resource<T> {
    case error(message: String)
    case loading
    case success(data: T)
}
