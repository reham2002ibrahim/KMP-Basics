//
//  ArticlesList.swift
//  Articles-iOS
//
//  Created by Yara Mohamed on 02/06/2025.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ArticleList: View {
    @State private var showPopover = false
    @ObservedObject var viewModel: ArticleListWrapper

    var body: some View {
        NavigationView {
            VStack {
                if viewModel.articleState.loading{
                    ProgressView()
                        .padding()
                } else if let error = viewModel.articleState.error, !error.isEmpty {
                    Text(error)
                        .foregroundColor(.red)
                        .padding()
                } else if viewModel.articleState.articles.isEmpty {
                    Text("No articles available.")
                        .foregroundColor(.gray)
                        .padding()
                } else {
                    ScrollView {
                        LazyVStack(spacing: 10) {
                            ForEach(viewModel.articleState.articles, id: \.title) { article in
                                ArticleView(article: article)
                            }
                        }
                        .padding(.horizontal)
                    }
                }
            }
            .navigationTitle("Articles")
            .toolbar {
                ToolbarItem(placement: .navigationBarTrailing) {
                    Button("Info") {
                        showPopover.toggle()
                    }
                }
            }
        }
        .popover(isPresented: $showPopover) {
            let greet = Greeting().greet()
            Text(greet)
                .padding()
        }
        .onAppear {
            viewModel.startObserving()
        }
    }
}

#Preview {
    ArticleList(viewModel:ArticleList.ArticleListWrapper())
}


