//
//  DetailView.swift
//  CMPSwiftUI
//
//  Created by Ali Taha on 15.12.2023.
//

import Foundation
import SwiftUI
import composeBase

struct DetailViewComposeEntry : UIViewControllerRepresentable {
    let holder: DetailScreenHolder
    
    func makeUIViewController(context: Context) -> UIViewController {
        return holder.viewController
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
    }
}

struct DetailView: View {
    private let holder: DetailScreenHolder
    
    init(
        coinId: String,
        coinSymbol: String,
        coinActivity: Bool
    ) {
        self.holder = DetailScreenHolder(
            coinId: coinId,
            coinSymbol: coinSymbol,
            coinActivity: coinActivity,
            onBackPressed: {
                NavigationManager.shared.pop()
            }
        )
    }
    
    var body: some View {
        ZStack {
            Color.black.ignoresSafeArea(.all)
            
            DetailViewComposeEntry(
                holder: holder
            )
            .navigationBarBackButtonHidden(true)
            .onDisappear {
                holder.release()
            }
            .edgesIgnoringSafeArea(.bottom)
        }
    }
}
