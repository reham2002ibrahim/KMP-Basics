import SwiftUI
import shared

struct ContentView: View {
	var body: some View {
        ArticleList(viewModel: ArticleList.ArticleListWrapper())
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
