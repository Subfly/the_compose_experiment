//
//  CoinDto.swift
//  BaseiOSSwitUI
//
//  Created by Ali Taha on 4.12.2023.
//

import Foundation

struct CoinDto: Codable, Identifiable {
    let id: String?
    let contract: String?
    let contracts: [ContractDto]?
    let description: String?
    let development_status: String?
    let first_data_at: String?
    let hardware_wallet: Bool?
    let hash_algorithm: String?
    let is_active: Bool?
    let is_new: Bool?
    let last_data_at: String?
    let links: LinksDto?
    let links_extended: [LinksExtendedDto]?
    let logo: String?
    let message: String?
    let name: String?
    let open_source: Bool?
    let org_structure: String?
    let parent: ParentDto?
    let platform: String?
    let proof_type: String?
    let rank: Int?
    let started_at: String?
    let symbol: String?
    let tags: [TagDto]?
    let team: [TeamDto]?
    let type: String?
    let whitepaper: WhitePaperDto?
}
