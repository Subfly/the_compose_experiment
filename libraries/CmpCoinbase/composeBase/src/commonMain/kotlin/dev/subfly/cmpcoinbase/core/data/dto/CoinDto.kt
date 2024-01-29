package dev.subfly.cmpcoinbase.core.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CoinDto(
    val id: String? = null,
    val contract: String? = null,
    val contracts: List<ContractDto>? = null,
    val description: String? = null,
    val development_status: String? = null,
    val first_data_at: String? = null,
    val hardware_wallet: Boolean? = null,
    val hash_algorithm: String? = null,
    val is_active: Boolean? = null,
    val is_new: Boolean? = null,
    val last_data_at: String? = null,
    val links: LinksDto? = null,
    val links_extended: List<LinksExtendedDto>? = null,
    val logo: String? = null,
    val message: String? = null,
    val name: String? = null,
    val open_source: Boolean? = null,
    val org_structure: String? = null,
    val parent: ParentDto? = null,
    val platform: String? = null,
    val proof_type: String? = null,
    val rank: Int? = null,
    val started_at: String? = null,
    val symbol: String? = null,
    val tags: List<TagDto>? = null,
    val team: List<TeamDto>? = null,
    val type: String? = null,
    val whitepaper: WhitePaperDto? = null
)
