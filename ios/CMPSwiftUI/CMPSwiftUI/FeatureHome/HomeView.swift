//
//  HomeView.swift
//  CMPSwiftUI
//
//  Created by Ali Taha on 15.12.2023.
//

import Foundation
import SwiftUI
import composeBase

struct HomeViewComposeEntry : UIViewControllerRepresentable {
    let holder: HomeScreenHolder
    
    func makeUIViewController(context: Context) -> UIViewController {
        return holder.viewController
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
    }
}

struct HomeView: View {
    private let holder = HomeScreenHolder(
        onClickItem: { coin in
            NavigationManager.shared.goToDetail(
                of: coin.id,
                coinSymbol: coin.symbol,
                coinActivity: coin.isActive
            )
        }
    )
    
    var body: some View {
        ZStack {
            Color.black.ignoresSafeArea(.all)
            
            HomeViewComposeEntry(
                holder: holder
            )
            .onDisappear {
                holder.release()
            }
            .edgesIgnoringSafeArea(.bottom)
        }
    }
}
