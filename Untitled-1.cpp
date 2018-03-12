#ifndef MATRIX_H
#define MATRIX_H

#include <vector>
using namespace std;

template <typemane Object>
class matrix 
{
    public:
        matrix(int rows, int cols):array( rows)
        {
            for(auto & thisRow : array)
                thisRow.reisze( cols );

        }
        matrix( vector<vector<Object>> v ): array{v}
          {}
        matrix( vector<vector<Object>> && v): array{std::move(v)}
          {}
        
        const vector<Object> & operator[](int row) const{return array[row];}

        vector<Object> & operator[](int row)
        {return array[row];}

        int numrows() const {return array.size();}

        int numrows() const {return numrows() ?array[0].size:0}
    private:
        vector<vector<Object>> array;
};
#endif