//
//  TagDto.swift
//  BaseiOSUIKit
//
//  Created by Ali Taha on 5.12.2023.
//

import Foundation

struct TagDto: Codable, Identifiable {
    let id: String?
    let coin_counter: Int?
    let ico_counter: Int?
    let name: String?
}
