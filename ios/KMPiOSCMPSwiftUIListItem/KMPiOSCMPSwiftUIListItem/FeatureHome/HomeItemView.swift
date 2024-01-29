//
//  HomeViewItem.swift
//  KMPiOSCMPSwiftUIListItem
//
//  Created by Ali Taha on 14.12.2023.
//

import Foundation
import SwiftUI
import composeBase

struct HomeItemView : UIViewControllerRepresentable {
    let coin: CoinHomeModel
    let onClickItem: (CoinHomeModel) -> Void
    
    func makeUIViewController(context: Context) -> UIViewController {
        return CreateHomeItemViewControllerKt.createHomeItemViewController(
            model: coin,
            onClickItem: onClickItem
        )
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
    }
}
