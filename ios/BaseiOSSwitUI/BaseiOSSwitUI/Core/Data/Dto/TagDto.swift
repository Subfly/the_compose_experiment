//
//  TagDto.swift
//  BaseiOSSwitUI
//
//  Created by Ali Taha on 4.12.2023.
//

import Foundation

struct TagDto: Codable, Identifiable {
    let id: String?
    let coin_counter: Int?
    let ico_counter: Int?
    let name: String?
}
